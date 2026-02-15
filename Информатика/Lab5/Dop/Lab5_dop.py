import polars as pl

df = pl.read_excel(
    "Lab5.xlsx",
    columns = [column for column in range(25)]
    )

columns = df.columns[6:24]  

df = df.with_columns(
    pl.concat_str([pl.col(c) for c in columns],separator = "")
    .alias("")
)

df = df.drop(df.columns[5:25])

df = df.fill_null("")

pl.Config.set_tbl_width_chars(10000)

pl.Config.set_tbl_rows(20)

pl.Config.set_tbl_hide_column_data_types(True)

pl.Config.set_tbl_formatting("ASCII_FULL")

df = df.rename({
    "__UNNAMED__0": " ",
    "A = ": "A = \nC = ",
    "10918": "10918\n19217",
    "__UNNAMED__3": "  ",
    "__UNNAMED__4": "   "
})

df = df.slice(1,12)

print(df)


